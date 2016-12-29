package talent.bearers.slimefusion.common.block

import com.teamwizardry.librarianlib.common.base.block.BlockMod
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import talent.bearers.slimefusion.common.core.EnumOreType
import talent.bearers.slimefusion.common.items.ModItems
import talent.bearers.slimefusion.common.lib.LibNames
import java.util.*

/**
 * @author WireSegal
 * Created at 5:13 PM on 12/29/16.
 */
class BlockMoonshard : BlockMod(LibNames.MOONSHARD, Material.IRON, *EnumOreType.getNamesFor(LibNames.MOONSHARD)) {
    companion object {
        val TYPE: PropertyEnum<EnumOreType> = PropertyEnum.create("ore", EnumOreType::class.java)
        val SIDE: PropertyEnum<EnumFacing> = PropertyEnum.create("side", EnumFacing::class.java, EnumFacing.UP, EnumFacing.DOWN)

        val AABB_DOWN = AxisAlignedBB(5.5 / 16, 0.0, 5.5 / 16, 10.5 / 16, 1.0 / 16, 10.5 / 16)
        val AABB_UP = AxisAlignedBB(5.5 / 16, 0.0, 5.5 / 16, 10.5 / 16, 1.0 / 16, 10.5 / 16)

        val RAND = Random()
    }

    init {
        setHardness(5.0F)
        setResistance(10.0F)
        soundType = SoundType.METAL
    }

    override fun canSilkHarvest(world: World?, pos: BlockPos?, state: IBlockState?, player: EntityPlayer?) = true

    override fun createStackedBlock(state: IBlockState)
            = ItemStack(this, 1, state.getValue(TYPE).ordinal)

    override fun isOpaqueCube(state: IBlockState) = false
    override fun isFullCube(state: IBlockState) = false

    override fun getPickBlock(state: IBlockState, target: RayTraceResult, world: World?, pos: BlockPos?, player: EntityPlayer?)
            = createStackedBlock(state)

    override fun getDrops(world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int): MutableList<ItemStack> {
        val type = state.getValue(TYPE)
        val totalDrops = Math.max(RAND.nextInt(fortune + 3) + 1, 1)
        val secondaryDrops = if (type.rareDrop != null) (RAND.nextDouble() * totalDrops * 0.5).toInt() else 0
        val ret = mutableListOf<ItemStack>()
        for (i in 0 until (totalDrops - secondaryDrops)) ret.add(ItemStack(ModItems.CRYSTAL, 1, type.mainDrop.ordinal))
        for (i in 0 until secondaryDrops) ret.add(ItemStack(ModItems.CRYSTAL, 1, type.rareDrop?.ordinal ?: 0))
        return ret
    }

    override fun createBlockState() = BlockStateContainer(this, TYPE, SIDE)
    override fun getMetaFromState(state: IBlockState) = state.getValue(TYPE).ordinal or (state.getValue(SIDE).index shl 3)
    override fun getStateFromMeta(meta: Int) = defaultState
            .withProperty(TYPE, EnumOreType[meta and 7])
            .withProperty(SIDE, EnumFacing.VALUES[(meta and 8) shr 3])

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos) =
            if (state.getValue(SIDE) == EnumFacing.UP) AABB_UP else AABB_DOWN
}
