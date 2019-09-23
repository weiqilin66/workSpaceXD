package com.transation;
/**
 * 
 * 
 * @author wayne
 * @Description 自定义异常
 * @version
 * @date
 */
public class BookStockException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookStockException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BookStockException(String message) {
		super(message);
		System.out.println(message);// TODO Auto-generated constructor stub
	}

	public BookStockException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
