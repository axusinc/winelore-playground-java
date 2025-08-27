package com.axus.id

import com.axus.id.model.aggregate.FullPermission
import com.axus.id.model.aggregate.FullPermissionPattern
import com.axus.id.model.aggregate.TokenSystem
import com.axus.id.model.aggregate.UserInformation
import com.axus.id.model.entity.Credentials
import com.axus.id.model.value.AUID
import com.axus.id.model.value.Password
import com.axus.id.model.value.Username
import com.axus.id.model.entity.ActivePermission
import com.axus.id.model.value.Permission
import com.axus.id.model.entity.Profile
import com.axus.id.model.entity.RefreshToken
import com.axus.id.model.entity.Token
import eth.likespro.commons.models.entity.CustomGeolocation
import com.axus.id.model.entity.Variation
import com.axus.id.model.value.Description
import com.axus.id.model.value.FirstName
import com.axus.id.model.value.LastName
import com.axus.id.model.value.Status
import eth.likespro.commons.models.Pagination

interface AXUSIDEndpoint {
    fun hasRefreshTokenActivePermission(refreshTokenId: RefreshToken.Id, fullPermission: FullPermission): Boolean
    fun hasTokenActivePermission(tokenId: Token.Id, fullPermission: FullPermission): Boolean

    // <============ ActivePermission Service ============>

    fun grantPermission(fullPermissionPattern: FullPermissionPattern, tokenId: Token.Id)
    fun filterPermissions(from: AUID?, to: AUID?, context: AUID?, permission: Permission?, pagination: Pagination, tokenId: Token.Id): List<ActivePermission>
    fun isPermissionExisting(permission: FullPermission, tokenId: Token.Id): Boolean

    // <============ Credentials Service ============>

    fun createCredentials(username: Username, password: Password): Credentials
    fun changeUsername(auid: AUID, newUsername: Username, tokenId: Token.Id)
    fun changePassword(auid: AUID, newPassword: Password, tokenId: Token.Id)
    fun getCredentialsByUsername(username: Username): Credentials?
    fun getCredentials(auid: AUID): Credentials?
    fun areCredentialsExisting(auid: AUID): Boolean

    // <============ Profile Service ============>

    fun getProfile(auid: AUID): Profile?

    // <============ Token Service ============>

    fun createTokenSystemWithCredentials(auid: AUID, password: Password, permissions: List<FullPermissionPattern>): TokenSystem
    fun createTokenSystemWithToken(auid: AUID, permissions: List<FullPermissionPattern>, tokenId: Token.Id): TokenSystem
    fun getRefreshToken(id: RefreshToken.Id): RefreshToken?
    fun getTokenByRefreshTokenId(refreshTokenId: RefreshToken.Id): Token?
    fun getToken(id: Token.Id): Token?
    fun filterRefreshTokenPermissions(refreshTokenId: RefreshToken.Id, from: AUID?, to: AUID?, context: AUID?, permission: Permission?, pagination: Pagination): List<ActivePermission>
    fun filterTokenPermissions(tokenId: Token.Id, from: AUID?, to: AUID?, context: AUID?, permission: Permission?, pagination: Pagination): List<ActivePermission>
    fun updateToken(refreshTokenId: RefreshToken.Id): Token?

    // <============ Variation Service ============>

    fun getDefaultVariationId(auid: AUID): Variation.Id?
    fun getDefaultVariation(auid: AUID): Variation?
    fun getVariation(variationId: Variation.Id, tokenId: Token.Id?): Variation?
    fun createVariation(
        auid: AUID,
        firstName: FirstName?,
        lastName: LastName?,
        status: Status?,
        description: Description?,
        location: CustomGeolocation?,
        tokenId: Token.Id
    ): Variation
    fun changeVariationFirstName(variationId: Variation.Id, firstName: FirstName?, tokenId: Token.Id)

    // <============ UserInformation Service ============>

    fun getUserInformation(auid: AUID, variationId: Variation.Id? = null, tokenId: Token.Id? = null): UserInformation?
}