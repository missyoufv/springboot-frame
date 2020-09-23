package cn.duw.frame.mq.rabbitmq.constant;

public class RabbitMqConstant {

    /**
     * 延迟队列
     */
    public static final String DELAY_QUEUE_NAME = "delay_queue";
    /**
     * 延迟队列绑定的交换机
     */
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange";

    /**
     * 延迟队列和交换机绑定key
     */
    public static final String DELAY_BINDING_KEY = "delay_binding";

    /**
     * 工作队列
     */
    public static final String WORK_QUEUE_NAME = "work_queue";

    /**
     * 工作队列绑定交换机
     */
    public static final String WORK_EXCHANGE_NAME = "work_exchange";

    /**
     * 工作隊列和交换机绑定key
     */
    public static final String WORK_RECEIVE_KEY = "work_receive";

    /**
     * debug 日志级别队列
     */
    public static final String DEBUG_QUEUE_NAME = "debug_queue";

    /**
     * info 日志级别队列
     */
    public static final String INFO_QUEUE_NAME = "info_queue";

    /**
     * error 日志级别队列
     */
    public static final String ERROR_QUEUE_NAME = "error_queue";

    /**
     * log level exchange
     */
    public static final String LOG_LEVEL_EXCHANGE = "log_level_exchange";


    public static final String LOG_LEVEL_ERROR = "error";

    public static final String LOG_LEVEL_IFNO = "info";

    public static final String LOG_LEVEL_DEBUG = "debug";


    public static final String LOG_EXCHANGE_NAME = "log_exchange";

    public static final String TEST_ACK_EXCHANGE_NAME = "test_ack_exchange";

    public static final String TEST_ACK_QUEUE_NAME = "test_queue_exchange";

}
