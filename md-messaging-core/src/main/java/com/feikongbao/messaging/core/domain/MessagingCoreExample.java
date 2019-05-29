package com.feikongbao.messaging.core.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author jinjun_luo
 * @Date 2019/4/26 13:59
 **/
public class MessagingCoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessagingCoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMessageUuidIsNull() {
            addCriterion("message_uuid is null");
            return (Criteria) this;
        }

        public Criteria andMessageUuidIsNotNull() {
            addCriterion("message_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andMessageUuidEqualTo(String value) {
            addCriterion("message_uuid =", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidNotEqualTo(String value) {
            addCriterion("message_uuid <>", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidGreaterThan(String value) {
            addCriterion("message_uuid >", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidGreaterThanOrEqualTo(String value) {
            addCriterion("message_uuid >=", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidLessThan(String value) {
            addCriterion("message_uuid <", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidLessThanOrEqualTo(String value) {
            addCriterion("message_uuid <=", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidLike(String value) {
            addCriterion("message_uuid like", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidNotLike(String value) {
            addCriterion("message_uuid not like", value, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidIn(List<String> values) {
            addCriterion("message_uuid in", values, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidNotIn(List<String> values) {
            addCriterion("message_uuid not in", values, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidBetween(String value1, String value2) {
            addCriterion("message_uuid between", value1, value2, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andMessageUuidNotBetween(String value1, String value2) {
            addCriterion("message_uuid not between", value1, value2, "messageUuid");
            return (Criteria) this;
        }

        public Criteria andExchangeIsNull() {
            addCriterion("exchange is null");
            return (Criteria) this;
        }

        public Criteria andExchangeIsNotNull() {
            addCriterion("exchange is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeEqualTo(String value) {
            addCriterion("exchange =", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotEqualTo(String value) {
            addCriterion("exchange <>", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeGreaterThan(String value) {
            addCriterion("exchange >", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeGreaterThanOrEqualTo(String value) {
            addCriterion("exchange >=", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLessThan(String value) {
            addCriterion("exchange <", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLessThanOrEqualTo(String value) {
            addCriterion("exchange <=", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLike(String value) {
            addCriterion("exchange like", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotLike(String value) {
            addCriterion("exchange not like", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeIn(List<String> values) {
            addCriterion("exchange in", values, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotIn(List<String> values) {
            addCriterion("exchange not in", values, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeBetween(String value1, String value2) {
            addCriterion("exchange between", value1, value2, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotBetween(String value1, String value2) {
            addCriterion("exchange not between", value1, value2, "exchange");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyIsNull() {
            addCriterion("routing_key is null");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyIsNotNull() {
            addCriterion("routing_key is not null");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyEqualTo(String value) {
            addCriterion("routing_key =", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyNotEqualTo(String value) {
            addCriterion("routing_key <>", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyGreaterThan(String value) {
            addCriterion("routing_key >", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyGreaterThanOrEqualTo(String value) {
            addCriterion("routing_key >=", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyLessThan(String value) {
            addCriterion("routing_key <", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyLessThanOrEqualTo(String value) {
            addCriterion("routing_key <=", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyLike(String value) {
            addCriterion("routing_key like", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyNotLike(String value) {
            addCriterion("routing_key not like", value, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyIn(List<String> values) {
            addCriterion("routing_key in", values, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyNotIn(List<String> values) {
            addCriterion("routing_key not in", values, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyBetween(String value1, String value2) {
            addCriterion("routing_key between", value1, value2, "routingKey");
            return (Criteria) this;
        }

        public Criteria andRoutingKeyNotBetween(String value1, String value2) {
            addCriterion("routing_key not between", value1, value2, "routingKey");
            return (Criteria) this;
        }

        public Criteria andReplyCodeIsNull() {
            addCriterion("reply_code is null");
            return (Criteria) this;
        }

        public Criteria andReplyCodeIsNotNull() {
            addCriterion("reply_code is not null");
            return (Criteria) this;
        }

        public Criteria andReplyCodeEqualTo(Integer value) {
            addCriterion("reply_code =", value, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeNotEqualTo(Integer value) {
            addCriterion("reply_code <>", value, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeGreaterThan(Integer value) {
            addCriterion("reply_code >", value, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_code >=", value, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeLessThan(Integer value) {
            addCriterion("reply_code <", value, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeLessThanOrEqualTo(Integer value) {
            addCriterion("reply_code <=", value, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeIn(List<Integer> values) {
            addCriterion("reply_code in", values, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeNotIn(List<Integer> values) {
            addCriterion("reply_code not in", values, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeBetween(Integer value1, Integer value2) {
            addCriterion("reply_code between", value1, value2, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_code not between", value1, value2, "replyCode");
            return (Criteria) this;
        }

        public Criteria andReplyTestIsNull() {
            addCriterion("reply_test is null");
            return (Criteria) this;
        }

        public Criteria andReplyTestIsNotNull() {
            addCriterion("reply_test is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTestEqualTo(String value) {
            addCriterion("reply_test =", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestNotEqualTo(String value) {
            addCriterion("reply_test <>", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestGreaterThan(String value) {
            addCriterion("reply_test >", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestGreaterThanOrEqualTo(String value) {
            addCriterion("reply_test >=", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestLessThan(String value) {
            addCriterion("reply_test <", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestLessThanOrEqualTo(String value) {
            addCriterion("reply_test <=", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestLike(String value) {
            addCriterion("reply_test like", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestNotLike(String value) {
            addCriterion("reply_test not like", value, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestIn(List<String> values) {
            addCriterion("reply_test in", values, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestNotIn(List<String> values) {
            addCriterion("reply_test not in", values, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestBetween(String value1, String value2) {
            addCriterion("reply_test between", value1, value2, "replyTest");
            return (Criteria) this;
        }

        public Criteria andReplyTestNotBetween(String value1, String value2) {
            addCriterion("reply_test not between", value1, value2, "replyTest");
            return (Criteria) this;
        }

        public Criteria andMessageSendIsNull() {
            addCriterion("message_send is null");
            return (Criteria) this;
        }

        public Criteria andMessageSendIsNotNull() {
            addCriterion("message_send is not null");
            return (Criteria) this;
        }

        public Criteria andMessageSendEqualTo(String value) {
            addCriterion("message_send =", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendNotEqualTo(String value) {
            addCriterion("message_send <>", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendGreaterThan(String value) {
            addCriterion("message_send >", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendGreaterThanOrEqualTo(String value) {
            addCriterion("message_send >=", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendLessThan(String value) {
            addCriterion("message_send <", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendLessThanOrEqualTo(String value) {
            addCriterion("message_send <=", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendLike(String value) {
            addCriterion("message_send like", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendNotLike(String value) {
            addCriterion("message_send not like", value, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendIn(List<String> values) {
            addCriterion("message_send in", values, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendNotIn(List<String> values) {
            addCriterion("message_send not in", values, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendBetween(String value1, String value2) {
            addCriterion("message_send between", value1, value2, "messageSend");
            return (Criteria) this;
        }

        public Criteria andMessageSendNotBetween(String value1, String value2) {
            addCriterion("message_send not between", value1, value2, "messageSend");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}