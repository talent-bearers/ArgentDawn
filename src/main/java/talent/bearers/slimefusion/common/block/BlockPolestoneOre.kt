package talent.bearers.slimefusion.common.block

import com.teamwizardry.librarianlib.common.base.block.BlockMod
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import talent.bearers.slimefusion.common.core.EnumPolestone
import talent.bearers.slimefusion.common.items.ModItems
import talent.bearers.slimefusion.common.lib.LibNames
import java.util.*

/**
 * @author WireSegal
 * Created at 9:08 PM on 12/28/16.
 */
class BlockPolestoneOre : BlockMod(LibNames.ORE, Material.ROCK, *EnumPolestone.getNamesFor(LibNames.ORE)) {
    companion object {
        val POLESTONE: PropertyEnum<EnumPolestone> = PropertyEnum.create("polestone", EnumPolestone::class.java)
    }

    init {
        setHardness(3.0F)
        setResistance(5.0F)
        soundType = SoundType.STONE
    }

    override fun getMetaFromState(state: IBlockState) = state.getValue(POLESTONE).ordinal
    override fun getStateFromMeta(meta: Int) = defaultState.withProperty(POLESTONE, EnumPolestone[meta])
    override fun createBlockState() = BlockStateContainer(this, POLESTONE)

    override fun quantityDropped(state: IBlockState, fortune: Int, random: Random): Int {
        return if (fortune > 0) this.quantityDropped(random) * (Math.max(random.nextInt(fortune + 2), 1))
        else this.quantityDropped(random)
    }

    override fun getExpDrop(state: IBlockState?, world: IBlockAccess?, pos: BlockPos?, fortune: Int): Int {
        val rand = if (world is World) world.rand else Random()
        return MathHelper.getRandomIntegerInRange(rand, 3, 7)
    }

    override fun damageDropped(state: IBlockState): Int {
        return getMetaFromState(state)
    }

    override fun getItemDropped(state: IBlockState?, rand: Random?, fortune: Int): Item? {
        return ModItems.GEM
    }

    override fun getPickBlock(state: IBlockState, target: RayTraceResult?, world: World?, pos: BlockPos?, player: EntityPlayer?): ItemStack {
        return ItemStack(this, 1, getMetaFromState(state))
    }

}
