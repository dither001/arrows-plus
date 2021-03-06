/*******************************************************************************
 * ConnectionHandler.java
 * Copyright (c) 2013 WildBamaBoy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package arrowsplus.core.forge;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import arrowsplus.core.ArrowsPlus;
import arrowsplus.core.util.PacketHelper;
import arrowsplus.core.util.object.UpdateHandler;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

/**
 * Handles connections between the client and server.
 */
public class ConnectionHandler implements IConnectionHandler
{
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) 
	{
		new Thread(new UpdateHandler(netHandler)).run();
		return;
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) 
	{
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) 
	{
		return;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager)
	{
		return;
	}

	@Override
	public void connectionClosed(INetworkManager manager) 
	{
		return;
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) 
	{
		if (!ArrowsPlus.instance.isIntegratedServer)
		{
			ArrowsPlus.instance.isDedicatedClient = true;
		}

		manager.addToSendQueue(PacketHelper.createLoginPacket(ArrowsPlus.instance.modPropertiesManager));
	}
}
