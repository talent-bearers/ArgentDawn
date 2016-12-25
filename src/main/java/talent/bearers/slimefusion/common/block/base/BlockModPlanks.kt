package talent.bearers.slimefusion.common.block.base

import com.teamwizardry.librarianlib.common.base.block.BlockMod
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.oredict.OreDictionary

/**
 * @author WireSegal
 * Created at 10:42 AM on 12/25/16.
 */
@Suppress("LeakingThis")
open class BlockModPlanks(name: String, vararg variants: String) : BlockMod(name, Material.WOOD, *variants) {
    init {
        soundType = SoundType.WOOD
        setHardness(2f)
        setResistance(5f)
        if (itemForm != null)
            for (variant in variants.indices)
                OreDictionary.registerOre("plankWood", ItemStack(this, 1, variant))
    }

    override fun getHarvestTool(state: IBlockState?): String? {
        return "axe"
    }

    override fun isToolEffective(type: String?, state: IBlockState?): Boolean {
        return type == "axe"
    }

    override fun getFlammability(world: IBlockAccess?, pos: BlockPos?, face: EnumFacing?) = 20
    override fun getFireSpreadSpeed(world: IBlockAccess?, pos: BlockPos?, face: EnumFacing?) = 5
}
