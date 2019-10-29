##Async Messaging Libraries and the Message Executor Application:
The submodules included in this module works around the RabbitMQ message broker,
we have client libraries(as the message publisher) to send message to RabbitMQ, 
and we have an application(a standalone JVM as the message receiver) listening the various queues to react upone the message arrival.

##What we set on the Queues and Messages?
    * All the queue are set to be durable, see EmailMessagingClientConfig.
    * All the messages are set to be peristent, see the logic of EmailMessageSender.MessagePostProcessor.
    * All the messages are set to be mandatory, which means the message must be routable, otherwise an exeception will be thrown and logged, see the logic of the companyRabbitTemplate from idl-plt-cfg module.

#### Libraries (as the message publisher):
    * idl-messaging-entity
      A module which houses entities shared on the both side the message queues.
    * idl-messaging-email-client
      A module that the client program can use to send emails asynchronously.
    * idl-messaging-wechat-client
    A module that the client program can use to send wechat messages asynchronously.

    
#### Application (as the message receiver):
    * au-messaging-executor(should be deployed as a standalone JVM).
    The RabbitMQ's messaging receiving side do the actural work of sending email/wechat messages.