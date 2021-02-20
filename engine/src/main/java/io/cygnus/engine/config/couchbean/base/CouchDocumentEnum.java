package io.cygnus.engine.config.couchbean.base;

public enum CouchDocumentEnum implements CouchDocument {

	AppConf("/app_conf"),

	AccountConf("/account_conf"),

	StrategyConf("/strategy_conf"),

	;

	private final String documentId;

	private CouchDocumentEnum(String documentId) {
		this.documentId = documentId;
	}

	@Override
	public String documentId() {
		return documentId;
	}

}
