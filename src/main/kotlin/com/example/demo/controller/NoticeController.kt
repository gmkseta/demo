package com.example.demo.controller

import com.example.demo.crud.AbstractCrudService
import com.example.demo.crud.CrudController
import com.example.demo.model.CreateNoticeRequestDto
import com.example.demo.model.Notice
import com.example.demo.model.NoticeResponseDto
import com.example.demo.model.UpdateNoticeRequestDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping


@Repository
interface NoticeRepository: JpaRepository<Notice, Long>

@Service
class NoticeService(repository: NoticeRepository):
    AbstractCrudService<Notice>(repository = repository)

@Controller
@RequestMapping("/api/notices")
class NoticeController(
    service: NoticeService
): CrudController<Notice, CreateNoticeRequestDto, UpdateNoticeRequestDto>(
    service = service,
    responseDto = NoticeResponseDto
)
