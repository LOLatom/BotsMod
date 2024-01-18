package com.thefreak.botsmod.client.Rendering.models;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import com.deltateam.deltalib.accessors.MinecraftAccessor;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;


public class JointPistonComponentModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "jointpistoncomponent"), "main");
	public final ModelPart Mechanism;

	public JointPistonComponentModel(ModelPart root) {
		this.Mechanism = root.getChild("Mechanism");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Mechanism = partdefinition.addOrReplaceChild("Mechanism", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition Component3 = Mechanism.addOrReplaceChild("Component3", CubeListBuilder.create(), PartPose.offset(-1.0F, 2.75F, 1.6F));

		PartDefinition Piston3 = Component3.addOrReplaceChild("Piston3", CubeListBuilder.create().texOffs(0, 5).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 4.75F, 0.5F));

		PartDefinition Joint5 = Component3.addOrReplaceChild("Joint5", CubeListBuilder.create().texOffs(0, 2).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.75F, 0.6F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Joint6 = Component3.addOrReplaceChild("Joint6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.75F, 1.35F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Component2 = Mechanism.addOrReplaceChild("Component2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 2.75F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition Piston2 = Component2.addOrReplaceChild("Piston2", CubeListBuilder.create().texOffs(6, 5).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 4.75F, 0.5F));

		PartDefinition Joint3 = Component2.addOrReplaceChild("Joint3", CubeListBuilder.create().texOffs(6, 2).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.75F, 0.6F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Joint4 = Component2.addOrReplaceChild("Joint4", CubeListBuilder.create().texOffs(6, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.75F, 1.35F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Component = Mechanism.addOrReplaceChild("Component", CubeListBuilder.create(), PartPose.offset(-1.0F, 2.75F, -1.5F));

		PartDefinition Piston = Component.addOrReplaceChild("Piston", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, 4.75F, 0.5F));

		PartDefinition Joint = Component.addOrReplaceChild("Joint", CubeListBuilder.create().texOffs(10, 2).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.75F, 0.6F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Joint2 = Component.addOrReplaceChild("Joint2", CubeListBuilder.create().texOffs(10, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7F, 0.75F, 1.35F, 0.0F, 0.0F, -0.7854F));

		PartDefinition Bar = Mechanism.addOrReplaceChild("Bar", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public ModelPart getFirstPiston() {
		return this.Mechanism.getChild("Component");
	}
	public ModelPart getSecondPiston() {
		return this.Mechanism.getChild("Component2");
	}
	public ModelPart getThirdPiston() {
		return this.Mechanism.getChild("Component3");
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float rotation = (float) Math.cos(ageInTicks * 0.4) * 10;
		float rotation2 = (float) Math.sin(ageInTicks * 0.4) * 10;
		float rotation3 = (float) Math.sin(ageInTicks * 0.4) * -10;

		getFirstPiston().zRot = (float) Math.toRadians(rotation);
		getSecondPiston().zRot = (float) Math.toRadians(rotation2);
		getThirdPiston().zRot = (float) Math.toRadians(rotation3);
	}


	public void copyFromArm(ModelPart arm) {
		this.Mechanism.x = arm.x;
		this.Mechanism.y = arm.y;
		this.Mechanism.z = arm.z;
		this.Mechanism.xRot = arm.xRot;
		this.Mechanism.yRot = arm.yRot;
		this.Mechanism.zRot = arm.zRot;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Mechanism.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}