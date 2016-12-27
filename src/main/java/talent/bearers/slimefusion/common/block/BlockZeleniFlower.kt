package talent.bearers.slimefusion.common.block

import com.teamwizardry.librarianlib.common.base.block.BlockMod
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.IPlantable
import net.minecraftforge.common.IShearable
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import talent.bearers.slimefusion.common.block.base.EnumStringSerializable
import talent.bearers.slimefusion.common.lib.LibNames
import java.util.*

/**
 * @author WireSegal
 * Created at 7:33 AM on 12/27/16.
 */
class BlockZeleniFlower : BlockMod(LibNames.FLOWER, Material.PLANTS, *Type.getNamesFor(LibNames.FLOWER)), IPlantable, IShearable {
    companion object {
        val TYPE: PropertyEnum<Type> = PropertyEnum.create("type", Type::class.java)
    }

    init {
        soundType = SoundType.PLANT
    }

    val FLOWER_AABB = AxisAlignedBB(4.8 / 16.0, 0.0, 4.8 / 16.0, 11.2 / 16.0, 9.6 / 16.0, 11.2 / 16.0)
    val TALL_GRASS_AABB = AxisAlignedBB(1.6 / 16.0, 0.0, 1.6 / 16.0, 14.4 / 16.0, 12.8 / 16.0, 14.4 / 16.0)


    override fun canPlaceBlockAt(worldIn: World, pos: BlockPos): Boolean {
        val soil = worldIn.getBlockState(pos.down())
        return super.canPlaceBlockAt(worldIn, pos) && soil.block.canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this)
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block) {
        super.neighborChanged(state, worldIn, pos, blockIn)
        this.checkAndDropBlock(worldIn, pos, state)
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        this.checkAndDropBlock(worldIn, pos, state)
    }

    fun checkAndDropBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        if (!this.canBlockStay(worldIn, pos)) {
            this.dropBlockAsItem(worldIn, pos, state, 0)
            worldIn.setBlockState(pos, Blocks.AIR.defaultState, 3)
        }
    }

    fun canBlockStay(worldIn: World, pos: BlockPos): Boolean {
        val soil = worldIn.getBlockState(pos.down())
        return soil.block.canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this)
    }

    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos)
            = if (state.getValue(TYPE) != Type.GRASS) FLOWER_AABB else TALL_GRASS_AABB
    override fun getCollisionBoundingBox(blockState: IBlockState, worldIn: World, pos: BlockPos) = NULL_AABB
    override fun isOpaqueCube(state: IBlockState) = false
    override fun isFullCube(state: IBlockState) = false
    override fun getPlantType(world: IBlockAccess, pos: BlockPos) = EnumPlantType.Plains
    override fun damageDropped(state: IBlockState) = getMetaFromState(state)
    override fun getMetaFromState(state: IBlockState) = state.getValue(TYPE).ordinal
    override fun getStateFromMeta(meta: Int) = defaultState.withProperty(TYPE, Type[meta])
    override fun createBlockState() = BlockStateContainer(this, TYPE)
    override fun isReplaceable(worldIn: IBlockAccess, pos: BlockPos) = worldIn.getBlockState(pos).getValue(TYPE) == Type.GRASS

    override fun onSheared(item: ItemStack?, world: IBlockAccess?, pos: BlockPos?, fortune: Int)
            = listOf(ItemStack(this, 1, Type.GRASS.ordinal))

    override fun isShearable(item: ItemStack, world: IBlockAccess, pos: BlockPos)
            = world.getBlockState(pos).getValue(TYPE) == Type.GRASS

    override fun getPlant(world: IBlockAccess, pos: BlockPos): IBlockState {
        val state = world.getBlockState(pos)
        if (state.block !== this) return defaultState
        return state
    }

    override fun getDrops(world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int): List<ItemStack> {
        if (state.getValue(TYPE) != Type.GRASS) return super.getDrops(world, pos, state, fortune)

        val ret = arrayListOf<ItemStack>()
        if (Block.RANDOM.nextInt(8) != 0) return ret
        val seed = ForgeHooks.getGrassSeed(Block.RANDOM, fortune)
        if (seed != null) ret.add(seed)
        return ret
    }

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer() = BlockRenderLayer.CUTOUT

    @SideOnly(Side.CLIENT)
    override fun getOffsetType() = EnumOffsetType.XZ

    enum class Type : EnumStringSerializable {
        WHITE, RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET, GRASS;

        companion object {
            operator fun get(meta: Int) = Type.values().getOrElse(meta) { WHITE }

            fun getNamesFor(prefix: String) = Type.values()
                    .map { prefix + "_" + it.getName() }
                    .toTypedArray()
        }
    }
}
