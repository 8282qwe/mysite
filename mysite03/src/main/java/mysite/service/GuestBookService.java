package mysite.service;

import mysite.repository.GuestbookLogRepository;
import mysite.repository.GuestbookRepository;
import mysite.service.impl.GuestBookImpl;
import mysite.vo.GuestbookVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestBookService implements GuestBookImpl {
    private final GuestbookRepository guestbookRepository;
    private final GuestbookLogRepository guestbookLogRepository;

    public GuestBookService(GuestbookRepository guestbookRepository, GuestbookLogRepository guestbookLogRepository) {
        this.guestbookRepository = guestbookRepository;
        this.guestbookLogRepository = guestbookLogRepository;
    }

    @Override
    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteContents(String id, String password) {
        GuestbookVo vo = guestbookRepository.findById(id);
        if (vo == null) {
            return;
        }

        if (guestbookRepository.deleteByIdAndPassword(Long.parseLong(id), password) == 1) {
            guestbookLogRepository.update(vo.getRegDate());
        }}

    @Override
    @Transactional
    public void addContents(GuestbookVo guestbookVo) {
        if (guestbookLogRepository.update() == 0) {
            guestbookLogRepository.insert();
        }

        guestbookRepository.insert(guestbookVo);
    }
}
