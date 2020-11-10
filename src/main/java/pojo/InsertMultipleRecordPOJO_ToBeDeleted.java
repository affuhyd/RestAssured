package pojo;

import java.util.List;

public class InsertMultipleRecordPOJO_ToBeDeleted {

	private List<InsertRecordPOJO> record;

	public InsertMultipleRecordPOJO_ToBeDeleted(List<InsertRecordPOJO> record) {
		this.record = record;
	}

	public List<InsertRecordPOJO> getMultipleRecords() {
		return this.record;
	}
}
