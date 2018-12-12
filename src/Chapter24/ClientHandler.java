package Chapter24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 ********************************************************************** 
 * @Title: ClientHandler.java
 * @Description: ChatHandler 同样是一个Runnable接口的实现
 * @author ankie
 * @date 2018年12月11日
 * @version 1.0
 **********************************************************************
 */
public class ClientHandler implements Runnable {

	// 客户端的socket连接
	private final Socket socket;

	// 客户端的identity
	private final String clientIdentify;

	// 通过构造函数传入客户端连接
	public ClientHandler(final Socket socket) {
		this.socket = socket;
		this.clientIdentify = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
	}

	@Override
	public void run() {
		try {
			this.chat();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	// 聊天程序
	private void chat() throws IOException {
		final BufferedReader bufferedReader = wrap2Reader(this.socket.getInputStream());
		final PrintStream printStream = wrap2Print(this.socket.getOutputStream());
		String received;
		while ((received = bufferedReader.readLine()) != null) {
			// 将客户端发送的消息输出给控制台
			System.out.printf("client:%s-message:%s\n", clientIdentify, received);
			if (received.equals("quit")) {
				// 如果客户端发起了quit的命令，则断开与客户端的连接
				write2Client(printStream, "client will close");
				socket.close();
				break;
			}
			// 向客户端发送消息
			write2Client(printStream, "Server:" + received);
		}
	}

	// 将输入的字节流封装成BufferedReader缓冲字符流
	private BufferedReader wrap2Reader(final InputStream inputStream) {
		return new BufferedReader(new InputStreamReader(inputStream));
	}

	// 将输出字节流封装成PrintStream
	private PrintStream wrap2Print(final OutputStream outputStream) {
		return new PrintStream(outputStream);
	}

	// 该方法主要用于向客户发送消息
	private void write2Client(final PrintStream pStream, final String message) {
		System.out.println(message);
		pStream.flush();
	}

	public static void main(final String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 13312);
		} catch (final UnknownHostException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		new Thread(new ClientHandler(socket)).run();
	}
}
