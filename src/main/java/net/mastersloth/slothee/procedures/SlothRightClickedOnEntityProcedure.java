package net.mastersloth.slothee.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
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
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SlotheeMod.LOGGER.warn("Failed to load dependency x for procedure SlothRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SlotheeMod.LOGGER.warn("Failed to load dependency y for procedure SlothRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SlotheeMod.LOGGER.warn("Failed to load dependency z for procedure SlothRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SlotheeMod.LOGGER.warn("Failed to load dependency world for procedure SlothRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((itemstack).getItem() == FullCupItem.block) && (!((entity instanceof LivingEntity) ? ((LivingEntity) entity).isChild() : false)))) {
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
		} else if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).isChild() : false)) {
			entity.getPersistentData().putDouble("Age", ((entity.getPersistentData().getDouble("Age")) + 50));
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, (int) 10, 3, 1, 3, 1);
			}
		}
	}
}
