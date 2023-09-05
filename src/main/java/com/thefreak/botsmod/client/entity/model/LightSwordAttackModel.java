package com.thefreak.botsmod.client.entity.model;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.thefreak.botsmod.entities.misc.LightSwordConstruct;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LightSwordAttackModel <T extends LightSwordConstruct> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "light_sword_attack"), "main");
	private final ModelPart Guard;

	public LightSwordAttackModel(ModelPart root) {
		this.Guard = root.getChild("Guard");
	}


	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Guard = partdefinition.addOrReplaceChild("Guard", CubeListBuilder.create().texOffs(20, 0).addBox(-6.0F, -28.0F, -1.5F, 12.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 7).addBox(-3.0F, -32.0F, -1.5F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 22).addBox(-1.0F, -43.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 14).addBox(-2.0F, -47.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition Right = Guard.addOrReplaceChild("Right", CubeListBuilder.create().texOffs(28, 31).addBox(-3.0F, -1.5F, -1.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -26.5F, 0.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition Left = Guard.addOrReplaceChild("Left", CubeListBuilder.create().texOffs(28, 22).addBox(0.0F, -1.5F, -1.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -26.5F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition Blade = Guard.addOrReplaceChild("Blade", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -24.0F, -1.0F, 8.0F, 37.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Guard.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}