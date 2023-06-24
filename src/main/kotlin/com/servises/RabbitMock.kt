package com.servises

import com.plugins.RabbitMQConfig
import com.rabbitmq.client.*
import java.nio.ByteBuffer

class RabbitMock {

    val connectionFactory = RabbitMQConfig.connectionFactory
    val connection = connectionFactory.newConnection()
    val channel = connection.createChannel()

//    val exchangeName = "user-tests"
//    val queueName = "check_user_id"
//    val routingKey = "check_user_id"

    fun chaeckUserId() {
//        channel.exchangeDeclare(exchangeName, "direct", true, true, false, null)
//
//        channel.queueDeclare(queueName, true, false, true, null)
//
//        channel.queueBind(queueName, exchangeName, routingKey)
//
//        val deliverCallback = DeliverCallback { consumerTag, delivery ->
//            val message =  ByteBuffer.wrap(delivery.body).long
//
//            println("Received message: $message")
//            // Здесь можно добавить логику обработки полученного сообщения
//        }
//
//        val cancelCallback = CancelCallback { consumerTag ->
//            println("Consumer $consumerTag has been canceled.")
//        }
//
//
//        channel.basicConsume(queueName, true, deliverCallback, cancelCallback)
//
//        channel.close()
//        connection.close()

//
//        val requestExchange = "user_tests"
//        val responseExchange = "user_tests"
//        val routingKey = "check_user_id"
//        val queueName = "check_user_id"
//
//        // Привязываем очередь к Request Exchange с указанным маршрутизационным ключом
//        channel.queueBind(queueName, requestExchange, routingKey)
//
//        val consumer = object : DefaultConsumer(channel) {
//            override fun handleDelivery(
//                consumerTag: String?,
//                envelope: Envelope?,
//                properties: AMQP.BasicProperties?,
//                body: ByteArray?
//            ) {
//                val userId = ByteBuffer.wrap(body).long
//
//                // Проверяем наличие пользователя с указанным userId
////                val userExists = checkUserExists(userId)
//                var response:Boolean = false
//                if (userId in 1..10){
//                    response = true
//                }
//
//                // Генерируем ответ
////                var response = if (userExists) "User exists" else "User not found"
//
//                // Отправляем ответ в Response Exchange с указанным correlationId
//                val responseProperties = AMQP.BasicProperties.Builder()
//                    .correlationId(properties?.correlationId)
//                    .build()
//
//                channel.basicPublish(responseExchange, "",
//                    responseProperties,if (response) byteArrayOf(1) else byteArrayOf(0)
//                )
//            }
//        }
//
//        channel.basicConsume(queueName, true, consumer)
//
//        // Закрываем соединение и канал после завершения работы
//        channel.close()
//        connection.close()
    }

}