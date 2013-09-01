Overview and motivation
=======
Finder is simple application for finding computer's IP in local network. As server it listens for UDP messages on specified port  and immediately responds with its IP. As client it sends broadcast message on the same port and waits for response.

Idea comes from real life problem: headless Raspberry Pi with DHCP and router that doesn’t show its DHCP clients ;)

Build
========
Standard maven project, to build it invoke <code>mvn install</code>

Usage
========
Server mode:
---------
<code>finder-1.0.jar -server [-port <port>]</code>
Client mode:
-----------
<code>finder-1.0.jar [-port <port>] [-broadcast <broadcast_address>] [-timeout <timeout>]</code>

Default port number is 12321.

Default timeout is 5000ms.

Default broadcast address is 255.255.255.255.
