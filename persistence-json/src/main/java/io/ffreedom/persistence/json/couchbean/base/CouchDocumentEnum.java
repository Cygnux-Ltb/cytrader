package io.ffreedom.persistence.json.couchbean.base;

public enum CouchDocumentEnum implements CouchDocument {



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
