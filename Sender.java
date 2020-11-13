import java.net.* ;
import java.io.* ;
public class Sender {
	public static void main(String[] args)
	{
		if(args.length != 4)
		{
			System.out.println("This math program requires 4 arguments :: 1.host adr 2.recPort 3.number1 4.number2");
		}
		else
		{
			try
			{
				InetAddress receiverHost = InetAddress.getByName(args[0]) ;
				int receiverPort = Integer.parseInt(args[1]) ;
				
				int num1 = Integer.parseInt(args[2]) ;
				int num2 = Integer.parseInt(args[3]) ;
				
				
				
				DatagramSocket mysocket = new DatagramSocket();
				ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
				DataOutputStream dos = new DataOutputStream(baos) ;
				
				dos.writeInt(num1);
				dos.writeInt(num2);
				
				byte[] datatoTransfer = baos.toByteArray() ;
				
				DatagramPacket datagram = new 
						DatagramPacket(datatoTransfer,datatoTransfer.length ,receiverHost ,receiverPort) ;
				
				System.out.println("Sending messsage and waiting for response") ;
				
				mysocket.send(datagram) ;
				
				byte[] receivebuffer = new byte[1000] ;
				DatagramPacket rdatagram = new DatagramPacket(receivebuffer,1000) ;
				mysocket.receive(rdatagram) ;
				System.out.println("Response Collected") ;
				
				ByteArrayInputStream bais = new ByteArrayInputStream(receivebuffer) ;
				DataInputStream dis = new DataInputStream(bais) ;
				
				int num = dis.readInt();
				System.out.println(num1 +"+"+num2+"="+num) ;
				mysocket.close() ;
					
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
}
