package com.plugins

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import java.nio.ByteBuffer

object RabbitMQConfig {

    val connectionFactory: ConnectionFactory by lazy {
        val factory = ConnectionFactory()
        factory.host = "localhost" // Замените на адрес вашего RabbitMQ сервера
        factory.port = 5672  // Порт RabbitMQ
        factory.username = "guest" // Имя пользователя
        factory.password = "guest" // Пароль
        factory
    }

    val connection = connectionFactory.newConnection()
    val channel = connection.createChannel()

    fun initRab() {


        val requestExchange = "user_tests"
        val routingKey = "check_user_id"
        val queueName = "check_user_id"

        channel.exchangeDeclare(requestExchange, "direct", true, true, false, null)
        channel.queueDeclare(queueName, true, false, false, null)

        // Привязываем очередь к Request Exchange с указанным маршрутизационным ключом
        channel.queueBind(queueName, requestExchange, routingKey)

        val consumer = object : DefaultConsumer(channel) {
            override fun handleDelivery(
                consumerTag: String?,
                envelope: Envelope?,
                properties: AMQP.BasicProperties?,
                body: ByteArray?
            ) {
                val userId = ByteBuffer.wrap(body).long
                println("Received message: $userId")
                // Проверяем наличие пользователя с указанным userId
                var response = false
                if (userId in 1..10){
                    response = true
                }

                println(properties?.replyTo)

                println(response)
                // Отправляем ответ в Response Exchange с указанным correlationId
                val responseProperties = AMQP.BasicProperties.Builder()
                    .correlationId(properties?.correlationId)
                    .build()

                channel.basicPublish( "", properties?.replyTo,
                    responseProperties,if (response) byteArrayOf(1) else byteArrayOf(0)
                )
            }
        }
        channel.basicConsume(queueName, true, consumer)
    }


    fun closeRab() {
        // Закрываем соединение и канал после завершения работы
        if(channel.isOpen){
            channel.close()
        }
        if(connection.isOpen){
            connection.close()
        }
    }
}