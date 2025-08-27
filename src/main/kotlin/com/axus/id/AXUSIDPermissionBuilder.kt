package com.axus.id

import com.axus.id.model.aggregate.FullPermission
import com.axus.id.model.entity.Credentials
import com.axus.id.model.entity.Variation
import com.axus.id.model.value.AUID
import com.axus.id.model.value.Permission

object AXUSIDPermissionBuilder {
    // <============ ActivePermission Service ============>

    fun grantPermission(from: AUID, context: AUID, grantee: AUID? = null) = FullPermission(
        from,
        grantee,
        Credentials.AXUSID.auid,
        Permission("activepermission:grantPermission:${context.value}")
    )

    fun filterIssuedPermissions(from: AUID, context: AUID?, grantee: AUID? = null) = FullPermission(
        from,
        grantee,
        Credentials.AXUSID.auid,
        Permission("activepermission:filterPermissions:${context?.value ?: "*"}:issued")
        // If context is null, it will be "*" and thus will pass only if the allowed context is `.*`
    )

    fun filterGrantedPermissions(to: AUID, context: AUID?, grantee: AUID? = null) = FullPermission(
        to,
        grantee,
        Credentials.AXUSID.auid,
        Permission("activepermission:filterPermissions:${context?.value ?: "*"}:granted")
    )

    // <============ Credentials Service ============>

    fun changeUsername(auid: AUID, grantee: AUID? = null) = FullPermission(
        auid,
        grantee,
        Credentials.AXUSID.auid,
        Permission("credentials:changeUsername")
    )

    fun changePassword(auid: AUID, grantee: AUID? = null) = FullPermission(
        auid,
        grantee,
        Credentials.AXUSID.auid,
        Permission("credentials:changePassword")
    )

    // <============ Token Service ============>

    fun createTokenSystem(auid: AUID, allowedPermissionsContext: AUID?, grantee: AUID? = null) = FullPermission(
        auid,
        grantee,
        Credentials.AXUSID.auid,
        Permission("token:createTokenSystem:${allowedPermissionsContext?.value ?: "*"}")
    )

    // <============ Variation Service ============>

    fun createVariation(auid: AUID, grantee: AUID? = null) = FullPermission(
        from = auid,
        to = grantee,
        context = Credentials.AXUSID.auid,
        permission = Permission("variation:createVariation")
    )
    fun getVariation(auid: AUID, variationId: Variation.Id, grantee: AUID? = null) = FullPermission(
        from = auid,
        to = grantee,
        context = Credentials.AXUSID.auid,
        permission = Permission("variation:getVariation:${variationId.value}")
    )
    fun changeVariationFirstName(auid: AUID, grantee: AUID? = null) = FullPermission(
        from = auid,
        to = grantee,
        context = Credentials.AXUSID.auid,
        permission = Permission("variation:changeVariation:firstName")
    )
}