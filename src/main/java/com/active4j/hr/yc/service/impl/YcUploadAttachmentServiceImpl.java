package com.active4j.hr.yc.service.impl;


import com.active4j.hr.yc.dao.YcUploadAttachmentDao;
import com.active4j.hr.yc.entity.YcUploadAttachmentEntity;
import com.active4j.hr.yc.service.YcUploadAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ycUploadAttachmentService")
@Transactional
public class YcUploadAttachmentServiceImpl extends ServiceImpl<YcUploadAttachmentDao, YcUploadAttachmentEntity> implements YcUploadAttachmentService {
}
