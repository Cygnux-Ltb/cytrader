package io.cygnus.runtime.config.couchbean.base;

public enum CouchDocumentEnum implements CouchDocument {

	AppConf("/redstone", "/app_conf"),

	AccountConf("/redstone", "/account_config"),

	StrategyConf("/redstone", "/strategy_conf"),

	;

	private String _database;
	private String _id;

	private CouchDocumentEnum(String _database, String _id) {
		this._database = _database;
		this._id = _id;
	}

	@Override
	public String _id() {
		return _id;
	}

	@Override
	public String _database() {
		return _database;
	}

}
