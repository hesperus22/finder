package pl.conceptit.finder;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class Finder
{
    public static void main(String[] args) throws Exception
    {
        List<String> arguments = Arrays.asList(args);
        InetAddress broadcast = InetAddress.getByName("255.255.255.255");
        int port = 12321;
        int timeout = 5000;

        if (arguments.contains("-port"))
        {
            port = Integer
                    .parseInt(arguments.get(arguments.indexOf("-port") + 1));
        }

        if (arguments.contains("-broadcast"))
        {
            broadcast = InetAddress.getByName(arguments.get(arguments
                    .indexOf("-port") + 1));
        }

        if (arguments.contains("-timeout"))
        {
            timeout = Integer.parseInt(arguments.get(arguments
                    .indexOf("-timeout") + 1));
        }

        if (arguments.contains("-server"))
        {
            System.out.println("Starting Finder server on port " + port + ".");

            while (true)
            {
                try (DatagramSocket socket = new DatagramSocket(port))
                {
                    while (true)
                    {
                        DatagramPacket packet = new DatagramPacket(new byte[1],
                                1);

                        socket.receive(packet);

                        System.out.println("Received query from "
                                + packet.getAddress());

                        socket.send(packet);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("Waiting for restart...");
                    Thread.sleep(30000);
                }
            }
        }
        else
        {
            try (DatagramSocket socket = new DatagramSocket())
            {
                DatagramPacket packet = new DatagramPacket(new byte[1], 1);
                packet.setAddress(broadcast);
                packet.setPort(port);

                socket.setSoTimeout(timeout);

                socket.send(packet);
                socket.receive(packet);

                System.out.println("Server address: " + packet.getAddress());
            }
        }
    }
}
