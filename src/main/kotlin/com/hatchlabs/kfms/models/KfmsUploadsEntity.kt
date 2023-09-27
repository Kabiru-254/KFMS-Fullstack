package com.hatchlabs.kfms.models

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "KFMS_UPLOADS")
class KfmsUploadsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "NAME_OF_FILE")
    var nameOfFile: String? = null

    @Column(name = "CREATED_BY")
    var createdBy: Long? = null

    @Column(name = "CREATED_ON")
    var createdOn: Date? = null

    @Column(name = "DESCRIPTION")
    var description: String? = null

    @Lob
    @Column(name = "DOCUMENT", columnDefinition = "LONGBLOB")
    var document: ByteArray? = null

    @Column(name = "CONTENT_TYPE")
    var contentType: String? = null

    @Column(name = "TABLE_NAME")
    var tableName: String? = null

    @Column(name = "ENTRY_ID")
    var entryId: Long? = null

    @Column(name = "DELETED_ON")
    var deletedOn: Date? = null

    @Column(name = "DELETED_BY")
    var deletedBy: Long? = null

}