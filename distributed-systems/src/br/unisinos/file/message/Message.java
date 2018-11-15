package br.unisinos.file.message;

import java.time.Instant;

public class Message {
	
	private String nodeId;
	private String msgId;
	private String timeUTC;
	private String msgTopic;
	private String msgContents;
	
	public Message() {}
	/*
	public Message(String nodeId, String msgId, String msgContents){
		this.nodeId = nodeId;
		this.msgId = msgId;
		Instant instant = Instant.now() ;
		this.timeUTC = instant.toString() ;
		this.msgContents = msgContents;
	}*/
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getTimeUTC() {
		return timeUTC;
	}
	public void setTimeUTC() {
		//get local UTC timestamp
		Instant instant = Instant.now() ;
		String timeUTC = instant.toString() ;
		
		this.timeUTC = timeUTC;
	}
	
	public String getMsgTopic() {
		return msgTopic;
	}
	public void setMsgTopic(String msgTopic) {
		this.msgTopic = msgTopic;
	}
	public String getMsgContents() {
		return msgContents;
	}
	public void setMsgContents(String msgContents) {
		this.msgContents = msgContents;
	}

	@Override
	public String toString() {
        return "{" +
                "nodeId='" + nodeId + '\'' +
                ", msgId=" + msgId +
                ", timeUTC=" + timeUTC +
                ", msgTopic=" + msgTopic +
                ", msgContents=" + msgContents +
                '}';
    }
}
