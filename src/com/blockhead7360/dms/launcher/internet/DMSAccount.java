package com.blockhead7360.dms.launcher.internet;

public class DMSAccount {

	private String id, username, status, statusColor, lastPay, nextPay, blockButton, blockReason;

	public DMSAccount(String id, String username, String status, String statusColor, String lastPay, String nextPay, String blockButton, String blockReason) {
		this.id = id;
		this.username = username;
		this.status = status;
		this.statusColor = statusColor;
		this.lastPay = lastPay;
		this.nextPay = nextPay;
		this.blockButton = blockButton;
		this.blockReason = blockReason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	public String getLastPay() {
		return lastPay;
	}

	public void setLastPay(String lastPay) {
		this.lastPay = lastPay;
	}

	public String getNextPay() {
		return nextPay;
	}

	public void setNextPay(String nextPay) {
		this.nextPay = nextPay;
	}

	public String getBlockButton() {
		return blockButton;
	}

	public void setBlockButton(String blockButton) {
		this.blockButton = blockButton;
	}

	public String getBlockReason() {
		return blockReason;
	}

	public void setBlockReason(String blockReason) {
		this.blockReason = blockReason;
	}
	
}
