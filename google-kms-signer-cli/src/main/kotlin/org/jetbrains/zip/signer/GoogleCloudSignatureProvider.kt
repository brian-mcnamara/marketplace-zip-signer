package org.jetbrains.zip.signer

import com.google.cloud.kms.v1.CryptoKeyVersionName
import com.google.cloud.kms.v1.Digest
import com.google.cloud.kms.v1.KeyManagementServiceClient
import org.jetbrains.zip.signer.metadata.SignatureAlgorithm
import org.jetbrains.zip.signer.signing.SignatureProvider


class GoogleCloudSignatureProvider(
    private val projectId: String,
    private val locationId: String,
    private val keyRingId: String,
    private val keyId: String,
    private val keyVersion: String,
    override val signatureAlgorithm: SignatureAlgorithm
) : SignatureProvider {
    override fun sign(dataToSign: ByteArray): ByteArray {
        KeyManagementServiceClient.create().use { client ->
            val keyName = CryptoKeyVersionName.of(projectId, locationId, keyRingId, keyId, keyVersion)
            val response = client.asymmetricSign(keyName, Digest.parseFrom(dataToSign))
            return response.signature.toByteArray()
        }
    }
}