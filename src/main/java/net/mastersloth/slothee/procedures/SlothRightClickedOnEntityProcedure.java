package net.mastersloth.slothee.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mastersloth.slothee.item.SlotheeCupItem;
import net.mastersloth.slothee.item.FullCupItem;
import net.mastersloth.slothee.SlotheeMod;

import java.util.Map;

public class SlothRightClickedOnEntityProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SlotheeMod.LOGGER.warn("Failed to load dependency entity for procedure SlothRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				SlotheeMod.LOGGER.warn("Failed to load dependency sourceentity for procedure SlothRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				SlotheeMod.LOGGER.warn("Failed to load dependency itemstack for procedure SlothRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (((itemstack).getItem() == FullCupItem.block)) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(FullCupItem.block);
				((PlayerEntity) sourceentity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) sourceentity).container.func_234641_j_());
			}
			if (!entity.world.isRemote())
				entity.remove();
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(SlotheeCupItem.block);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		}
	}
}
