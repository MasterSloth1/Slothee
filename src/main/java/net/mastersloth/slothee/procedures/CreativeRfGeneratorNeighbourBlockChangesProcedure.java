package net.mastersloth.slothee.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;

import net.mastersloth.slothee.SlotheeMod;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;

public class CreativeRfGeneratorNeighbourBlockChangesProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				SlotheeMod.LOGGER.warn("Failed to load dependency x for procedure CreativeRfGeneratorNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				SlotheeMod.LOGGER.warn("Failed to load dependency y for procedure CreativeRfGeneratorNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				SlotheeMod.LOGGER.warn("Failed to load dependency z for procedure CreativeRfGeneratorNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SlotheeMod.LOGGER.warn("Failed to load dependency world for procedure CreativeRfGeneratorNeighbourBlockChanges!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private IWorld world;
			public void start(IWorld world, int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
				this.world = world;
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks)
						run();
				}
			}

			private void run() {
				if (((new Object() {
					public boolean canReceiveEnergy(IWorld world, BlockPos pos) {
						AtomicBoolean _retval = new AtomicBoolean(false);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.canReceive()));
						return _retval.get();
					}
				}.canReceiveEnergy(world, new BlockPos((int) x, (int) y, (int) z))) && ((new Object() {
					public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
						AtomicInteger _retval = new AtomicInteger(0);
						TileEntity _ent = world.getTileEntity(pos);
						if (_ent != null)
							_ent.getCapability(CapabilityEnergy.ENERGY, null)
									.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
						return _retval.get();
					}
				}.receiveEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 1000)) >= 100))) {
					while (((new Object() {
						public int receiveEnergySimulate(IWorld world, BlockPos pos, int _amount) {
							AtomicInteger _retval = new AtomicInteger(0);
							TileEntity _ent = world.getTileEntity(pos);
							if (_ent != null)
								_ent.getCapability(CapabilityEnergy.ENERGY, null)
										.ifPresent(capability -> _retval.set(capability.receiveEnergy(_amount, true)));
							return _retval.get();
						}
					}.receiveEnergySimulate(world, new BlockPos((int) x, (int) y, (int) z), (int) 1000)) >= 100)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							int _amount = (int) 100;
							if (_ent != null)
								_ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.receiveEnergy(_amount, false));
						}
					}
				}
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, (int) 20);
	}
}
