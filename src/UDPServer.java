
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPServer {

	private static int threads = 0;
	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket aSocket = null;
		int maxThreads = 5;
		boolean startThread = true;
		StdinThread server = null;
		/*
		if (args.length < 1) {
			System.out.println("Usage: java UDPServer <Port Number>");
			System.exit(1);
		}
		*/
		
		if (args.length == 1) {
		
			try {
				int socket_no = Integer.parseInt(args[0]);
				aSocket = new DatagramSocket(socket_no);
				System.out.println(InetAddress.getLocalHost().getHostAddress() + " " + socket_no);
	
				
				
				
				while (true) {
					byte[] buffer = new byte[1000];
					DatagramPacket request = new DatagramPacket(buffer,
							buffer.length);
	
					aSocket.receive(request);
	
					System.out.println("[Contact from " + request.getAddress().getHostAddress()
							+ ":" + request.getPort() + "]");
					System.out.println(new String(request.getData()));
	
					// construct reply data
					String replyinfo = InetAddress.getLocalHost().getHostAddress();
					replyinfo += (" " + socket_no + '\n');
					byte[] replyData = replyinfo.getBytes();
	
					// send the data back
					DatagramPacket reply = new DatagramPacket(replyData,
							replyData.length, request.getAddress(),
							request.getPort());
					aSocket.send(reply);
					
					
					if (startThread) {
						server = new StdinThread(aSocket, 
								request.getAddress(), request.getPort());
						new Thread(server).start();
						startThread = false;
					} else {
						server.set(request.getAddress(), request.getPort());
					}
					
					/*
					StdinThread server = new StdinThread(aSocket, 
							request.getAddress(), request.getPort());
					new Thread(server).start();
					
					*/
					
					
				}
			} catch (SocketException e) {
				System.out.println("Socket: " + e.getMessage());
			}
			
			finally {
				if (aSocket != null)
					aSocket.close();
			}
		
		} else {
			
			
			
			
			
			
			
			try {
		        
				aSocket = new DatagramSocket();
		        InetAddress aHost = InetAddress.getByName(args[0]);
		        int serverPort = Integer.valueOf(args[1]).intValue();
		        
		        
		        
		        String info = InetAddress.getLocalHost().getHostAddress() 
		        		+ " " + serverPort + '\n';
		        
		        byte[] data = info.getBytes();
		        DatagramPacket request =
		               new DatagramPacket(data, data.length, aHost, serverPort);
		        aSocket.send(request);
		        
		        
		        
		        
		        while (true) {
		        	byte[] buffer = new byte[1000];
		        	DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			        aSocket.receive(reply);
			        System.out.println(new String(reply.getData()));
			        StdinThread thread = new StdinThread(aSocket, aHost, serverPort);
					new Thread(thread).start();
		        }

		        
			}
		      catch (SocketException e) {
		        System.out.println("Socket: " + e.getMessage());
		      }
		      catch (IOException e) {
		        System.out.println("IO: " + e.getMessage());
		      }
			finally {
		        if (aSocket != null)
		        	aSocket.close();
			}
			
		}
		
		
/*
		DatagramSocket aSocket = null;
		int maxThreads = 5;
		
		if (args.length < 1) {
			System.out.println("Usage: java UDPServer <Port Number>");
			System.exit(1);
		}
		try {
			int socket_no = Integer.parseInt(args[0]);
			aSocket = new DatagramSocket(socket_no);
			System.out.println(InetAddress.getLocalHost().getHostAddress() + " " + socket_no);

			while (true) {
				if(threads < maxThreads) {
					SeverThread server = new SeverThread(aSocket);
					new Thread(server).start();
					threads++;
				}
				
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		}
		
		finally {
			if (aSocket != null)
				aSocket.close();
		}
*/
	}
	
	
	
	public static class PacketThread implements Runnable {
		
		private DatagramSocket socket;
		
		public PacketThread(DatagramSocket soc) {
			socket = soc;
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public static class StdinThread implements Runnable {

		private DatagramSocket socket;
		private InetAddress addr;
		private int port;
		
		public StdinThread(DatagramSocket soc, InetAddress a, int p) {
			socket = soc;
			addr = a;
			port = p;
		}
		
		public void set(InetAddress a, int p) {
			addr = a;
			port = p;
		}
		
		@Override
		public void run() {
			Scanner console = new Scanner(System.in);
			while (console.hasNextLine()) {

				// read lines and send it back
				String input = console.nextLine();
				byte[] lines = input.getBytes();
				DatagramPacket text = new DatagramPacket(lines,
						lines.length, addr, port);
				
				try {
					socket.send(text);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
	
	
	
	
	
/*
	public static class SeverThread implements Runnable {
		private DatagramSocket socket = null;

		public SeverThread(DatagramSocket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			byte[] buffer = new byte[1000];

			try {
				DatagramPacket request = new DatagramPacket(buffer,
						buffer.length);

				socket.receive(request);

				System.out.println("[Contact from " + request.getAddress()
						+ ":" + request.getPort());
				System.out.println(request.getData());

				// construct reply data
				String replyinfo = socket.getLocalAddress().getHostAddress();
				replyinfo += (" " + socket.getPort() + '\n');
				byte[] replyData = replyinfo.getBytes();

				// send the data back
				DatagramPacket reply = new DatagramPacket(replyData,
						replyData.length, request.getAddress(),
						request.getPort());
				socket.send(reply);

				Scanner console = new Scanner(System.in);
				while (console.hasNextLine()) {

					// read lines and send it back
					String input = console.nextLine();
					byte[] lines = input.getBytes();
					DatagramPacket text = new DatagramPacket(lines,
							lines.length, request.getAddress(),
							request.getPort());
					socket.send(text);
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

	}
*/
}
