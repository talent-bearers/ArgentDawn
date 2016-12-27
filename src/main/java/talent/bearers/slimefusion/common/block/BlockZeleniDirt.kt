package talent.bearers.slimefusion.common.block

import com.teamwizardry.librarianlib.common.base.block.BlockMod
import net.minecraft.block.IGrowable
import net.minecraft.block.SoundType
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.EnumPlantType
import net.minecraftforge.common.IPlantable
import talent.bearers.slimefusion.common.block.base.EnumStringSerializable
import talent.bearers.slimefusion.common.lib.LibNames
import java.util.*

/**
 * @author WireSegal
 * Created at 7:33 AM on 12/27/16.
 */
class BlockZeleniDirt(name: String, val barren: Boolean = name == LibNames.SHALEDUST) : BlockMod(name, Material.GROUND, *EnumDirtVariant.getNamesFor(name)), IGrowable {

    init {
        setHardness(0.5F)
        tickRandomly = true
    }

    companion object {
        val TYPE: PropertyEnum<EnumDirtVariant> = PropertyEnum.create("type", EnumDirtVariant::class.java)
    }

    override fun getSoundType(state: IBlockState, world: World, pos: BlockPos, entity: Entity?): SoundType
            = if (state.getValue(TYPE) == EnumDirtVariant.DIRT) SoundType.GROUND else SoundType.PLANT
    override fun getMapColor(state: IBlockState) =
            if (state.getValue(TYPE) == EnumDirtVariant.DIRT) {
                if (barren)
                    MapColor.GRAY
                else
                    MapColor.LIGHT_BLUE
            } else {
                if (barren)
                    MapColor.RED
                else
                    MapColor.GRASS
            }
    override fun createBlockState() = BlockStateContainer(this, TYPE)
    override fun damageDropped(state: IBlockState) = state.getValue(TYPE).dropped
    override fun getPickBlock(state: IBlockState, target: RayTraceResult?, world: World?, pos: BlockPos?, player: EntityPlayer?)
            = ItemStack(this, 1, getMetaFromState(state))
    override fun getMetaFromState(state: IBlockState) = state.getValue(TYPE).ordinal
    override fun getStateFromMeta(meta: Int) = defaultState.withProperty(TYPE, EnumDirtVariant[meta])

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!worldIn.isRemote && state.getValue(TYPE) == EnumDirtVariant.GRASS) {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2) {
                worldIn.setBlockState(pos, state.withProperty(TYPE, EnumDirtVariant.DIRT))
            } else {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                    for (i in 0..3) {
                        val blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1)

                        if (blockpos.y >= 0 && blockpos.y < 256 && !worldIn.isBlockLoaded(blockpos)) {
                            return
                        }

                        val iblockstate = worldIn.getBlockState(blockpos.up())
                        val iblockstate1 = worldIn.getBlockState(blockpos)

                        if (iblockstate1.block == this && iblockstate1.getValue(TYPE) == EnumDirtVariant.DIRT && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2) {
                            worldIn.setBlockState(blockpos, state)
                        }
                    }
                }
            }
        }
    }

    override fun canSustainPlant(state: IBlockState, world: IBlockAccess, pos: BlockPos, direction: EnumFacing, plantable: IPlantable): Boolean {
        return plantable.getPlantType(world, pos.offset(direction)) == EnumPlantType.Plains
    }

    override fun canUseBonemeal(worldIn: World, rand: Random, pos: BlockPos, state: IBlockState): Boolean {
        return !barren || state.getValue(TYPE) == EnumDirtVariant.DIRT
    }

    override fun grow(worldIn: World, rand: Random, pos: BlockPos, state: IBlockState) {
        if (state.getValue(TYPE) == EnumDirtVariant.DIRT) {
            worldIn.setBlockState(pos, state.withProperty(TYPE, EnumDirtVariant.GRASS))
        } else {
            val empty = pos.up()

            for (i in 0..127) {
                var position = empty
                var j = 0

                while (true) {
                    if (j >= i / 16) {
                        if (worldIn.isAirBlock(position)) {
                            if (rand.nextInt(8) == 0) {
                                worldIn.getBiome(position).plantFlower(worldIn, rand, position)
                            } else {
                                val grass = ModBlocks.FLOWER.defaultState.withProperty(BlockZeleniFlower.TYPE, BlockZeleniFlower.Type.GRASS)

                                if (ModBlocks.FLOWER.canBlockStay(worldIn, position)) {
                                    worldIn.setBlockState(position, grass, 3)
                                }
                            }
                        }

                        break
                    }

                    position = position.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1)

                    if (worldIn.getBlockState(position.down()).block != this || worldIn.getBlockState(position).isNormalCube) {
                        break
                    }

                    ++j
                }
            }
        }
    }

    override fun canGrow(worldIn: World, pos: BlockPos, state: IBlockState, isClient: Boolean): Boolean {
        return canUseBonemeal(worldIn, worldIn.rand, pos, state)
    }

    enum class EnumDirtVariant : EnumStringSerializable {
        DIRT, GRASS(0);

        val dropped: Int

        constructor(dropped: Int) {
            this.dropped = dropped
        }
        constructor() {
            dropped = ordinal
        }

        companion object {
            operator fun get(meta: Int) = EnumDirtVariant.values().getOrElse(meta) { DIRT }

            fun getNamesFor(prefix: String) = EnumDirtVariant.values()
                    .map { prefix + "_" + it.getName() }
                    .toTypedArray()
        }
    }
}
