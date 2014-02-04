package mariculture.fishery.gui;

import mariculture.Mariculture;
import mariculture.core.gui.GuiMariculture;
import mariculture.core.gui.feature.Feature;
import mariculture.core.helpers.KeyBindingHelper;
import mariculture.core.network.Packet116GUIClick;
import mariculture.fishery.blocks.TileFishTank;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiFishTank extends GuiMariculture {
	public TileFishTank tile;
	
	public GuiFishTank(InventoryPlayer player, TileFishTank tile) {
		super(new ContainerFishTank(tile, player), "fishtank", 52);
		
		this.tile = tile;
	}
	
	@Override
	public int getX() {
		return 22;
	}
	
	@Override
	protected void onMouseClick(int x, int y)  {
		if(mouseX >= -18 && mouseX <= 2 && mouseY >= 104 && mouseY <= 124) {
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
					new Packet116GUIClick(tile.xCoord, tile.yCoord, tile.zCoord, tile.previous).build());
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
					new Packet116GUIClick(tile.xCoord, tile.yCoord, tile.zCoord, KeyBindingHelper.getPlayer().entityId).build());
			KeyBindingHelper.getPlayer().openGui(Mariculture.instance, -1, tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord);
		}
		
		if(mouseX >= 172 && mouseX <= 192 && mouseY >= 104 && mouseY <= 124) {
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
					new Packet116GUIClick(tile.xCoord, tile.yCoord, tile.zCoord, tile.next).build());
			Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(
					new Packet116GUIClick(tile.xCoord, tile.yCoord, tile.zCoord, KeyBindingHelper.getPlayer().entityId).build());
			KeyBindingHelper.getPlayer().openGui(Mariculture.instance, -1, tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord);
		}
    }
	
	@Override
	public void drawForeground() {
		fontRenderer.drawString("Page: " + (tile.thePage + 1) + "/" + tile.MAX_PAGES, 100, this.ySize - 96 + 3, 4210752);
		fontRenderer.drawString("Page: " + (tile.thePage + 1) +  "/" + tile.MAX_PAGES, 100, nameHeight, 4210752);
	}
	
	@Override
	public void drawBackground(int x, int y) {
		mc.renderEngine.bindTexture(Feature.texture);
		drawTexturedModalRect(x - 18, y + 103, 176, 99, 21, 22);
		drawTexturedModalRect(x + 173, y + 103, 176, 122, 21, 22);
		drawTexturedModalRect(x - 18 + 3, y + 103 + 2, 54, 220, 18, 18);
		if(mouseX >= -18 && mouseX <= 2 && mouseY >= 104 && mouseY <= 124)
			drawTexturedModalRect(x - 18 + 3, y + 103 + 2, 0, 220, 18, 18);
		
		drawTexturedModalRect(x + 173, y + 103 + 2, 36, 220, 18, 18);
		if(mouseX >= 172 && mouseX <= 192 && mouseY >= 104 && mouseY <= 124)
			drawTexturedModalRect(x + 173, y + 103 + 2, 18, 220, 18, 18);
		mc.renderEngine.bindTexture(TEXTURE);
	}
}
