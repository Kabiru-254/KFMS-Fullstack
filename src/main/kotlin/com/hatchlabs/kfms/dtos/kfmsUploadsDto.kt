package com.hatchlabs.kfms.dtos

import java.sql.Date

class kfmsUploadsDto {
    var id: Long? = null
    var nameOfFile: String? = null
    var createdBy: Long? = null
    var createdOn: Date? = null
    var description: String? = null
    var document: ByteArray? = null
    var contentType: String? = null
    var tableName: String? = null
    var entryId: Long? = null
    var deletedOn: Date? = null
    var deletedBy: Long? = null
}