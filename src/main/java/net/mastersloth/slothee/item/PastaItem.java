
package net.mastersloth.slothee.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mastersloth.slothee.SlotheeModElements;

@SlotheeModElements.ModElement.Tag
public class PastaItem extends SlotheeModElements.ModElement {
	@ObjectHolder("slothee:pasta")
	public static final Item block = null;
	public PastaItem(SlotheeModElements instance) {
		super(instance, 11);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(4).saturation(0.3f).build()));
			setRegistryName("pasta");
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.EAT;
		}
	}
}
