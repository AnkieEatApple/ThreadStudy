package Chapter24;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Chapter08.BasicThreadPool;
import Chapter08.ThreadPool;

/**
 ********************************************************************** 
 * @Title: ChatServer.java
 * @Description: 聊天的服务端处理聊天信息的类
 * @author ankie
 * @date 2018年12月11日
 * @version 1.0
 **********************************************************************
 */
public class ChatServer {

	// 服务端的端口号
	private final int port;

	// 定义线程池，该线程池是在第八章中定义的
	private ThreadPool threadPool;

	// 服务端的Socket
	private ServerSocket serverSocket;

	// 通过构造函数传入端口
	public ChatServer(final int port) {
		this.port = port;
	}

	// 默认使用的13312端口
	public ChatServer() {
		this(13312);
	}

	public void startServer() throws IOException {
		// 创建线程池，初始化一个线程，核心线程的数量是2， 最大的线程书为4，阻塞队列中最大可加入的任务是1000个任务
		this.threadPool = new BasicThreadPool(1, 4, 2, 1000);
		this.serverSocket = new ServerSocket(port);
		this.serverSocket.setReuseAddress(true);
		System.out.println("Chat server is started and listen at port: " + port);
		this.listen();
	}

	private void listen() throws IOException {
		while (true) {
			// accept方法是阻塞方法，当有信任的连接进入时，才会返回，并且返回的是客户端的连接
			final Socket client = serverSocket.accept();
			// 将客户端连接作为一个Request封装成对应的handler，然后提交给线程池
			this.threadPool.execute(new ClientHandler(client));
		}
	}

	public static void main(final String[] args) {
		final ChatServer chatServer = new ChatServer();
		try {
			chatServer.startServer();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
