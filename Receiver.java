//UDP Math Server :: BIPLABA SAMANTARAY
import java.net.* ;
import java.io.* ;
public class Receiver {

	public static void main(String[] args) {
		// This program takes the server port as the command line argument 
		
		
		if(args.length != 1)
		{
			System.out.println("This program requires a port number as command line arguments") ;
		}
		else
		{
			int port = Integer.parseInt(args[0]) ;
			final int MAX_LEN = 1000 ;
			try
			{
				while(true)
				{
					//
					DatagramSocket mySocket = new DatagramSocket(port) ;
					byte[] buffer =  new byte[MAX_LEN] ;
					DatagramPacket datagram = new DatagramPacket(buffer,MAX_LEN);
					System.out.println("The receiver is waiting for messege") ;
					
					mySocket.receive(datagram) ;
					ByteArrayInputStream bais = new ByteArrayInputStream(buffer) ;
					DataInputStream dis = new DataInputStream(bais) ;
					
					int num1 = dis.readInt();
					int num2 = dis.readInt();
					num1 = num1+num2 ;
					
					
					InetAddress senderAddress = datagram.getAddress();
				    int senderPort = datagram.getPort();
					
					//InetAddress host = InetAddress.getByName(adr) ;
					ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
					DataOutputStream dos = new DataOutputStream(baos) ;
					
					dos.writeInt(num1);
					byte[] datatoTransfer = baos.toByteArray() ;
					
					
					DatagramPacket sdatagram = new 
							DatagramPacket(datatoTransfer,datatoTransfer.length,senderAddress,senderPort ) ;
					mySocket.send(sdatagram) ;
					System.out.println("Operation completed :: Server sent back the response to the client") ;
					mySocket.close();
				
				
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

	}

}
