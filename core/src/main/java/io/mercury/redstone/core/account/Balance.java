package io.mercury.redstone.core.account;

/**
 * 
 * @author yellow013
 * 
 */
@Deprecated
public final class Balance {

	private int margin = 0;
	private int credit = 0;

	public Balance(int margin) {
		this.margin = margin;
	}

	public Balance(int margin, int credit) {
		this.margin = margin;
		this.credit = credit;
	}

	public int margin() {
		return margin;
	}

	public int credit() {
		return credit;
	}

	public Balance setMargin(int margin) {
		this.margin = margin;
		return this;
	}

	public Balance setCredit(int credit) {
		this.credit = credit;
		return this;
	}

	private static final String str0 = "{\"margin\" : ";
	private static final String str1 = ", \"credit\" : ";
	private static final String str2 = "}";

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(90);
		builder.append(str0);
		builder.append(margin);
		builder.append(str1);
		builder.append(credit);
		builder.append(str2);
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}

}