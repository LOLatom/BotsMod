package com.thefreak.botsmod.client.Rendering.models;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LowerOuterSteelPlateModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "loweroutersteelplate"), "main");
	private final ModelPart LowerOuterSteelPlate;

	public LowerOuterSteelPlateModel(ModelPart root) {
		this.LowerOuterSteelPlate = root.getChild("LowerOuterSteelPlate");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LowerOuterSteelPlate = partdefinition.addOrReplaceChild("LowerOuterSteelPlate", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Lay1 = LowerOuterSteelPlate.addOrReplaceChild("Lay1", CubeListBuilder.create().texOffs(13, 9).addBox(-2.2139F, -1.0902F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.75F, 0.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition Lay2 = LowerOuterSteelPlate.addOrReplaceChild("Lay2", CubeListBuilder.create().texOffs(13, 2).addBox(-2.1974F, -0.9435F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-1.75F, 5.7F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition Lay3 = LowerOuterSteelPlate.addOrReplaceChild("Lay3", CubeListBuilder.create().texOffs(0, 0).addBox(-2.2271F, -0.7943F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(-1.25F, 6.7F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition Lay4 = LowerOuterSteelPlate.addOrReplaceChild("Lay4", CubeListBuilder.create().texOffs(0, 7).addBox(-2.697F, -0.9653F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(-0.11F)), PartPose.offset(-0.05F, 5.7F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	public void copyFromArm(ModelPart arm) {
		this.LowerOuterSteelPlate.x = arm.x;
		this.LowerOuterSteelPlate.y = arm.y;
		this.LowerOuterSteelPlate.z = arm.z;
		this.LowerOuterSteelPlate.xRot = arm.xRot;
		this.LowerOuterSteelPlate.yRot = arm.yRot;
		this.LowerOuterSteelPlate.zRot = arm.zRot;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		LowerOuterSteelPlate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}