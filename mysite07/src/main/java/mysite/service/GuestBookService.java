package mysite.service;

import lombok.AllArgsConstructor;
import mysite.repository.GuestbookLogRepository;
import mysite.repository.GuestbookRepository;
import mysite.service.impl.GuestBookImpl;
import mysite.vo.GuestbookVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GuestBookService implements GuestBookImpl {
    private final GuestbookRepository guestbookRepository;
    private final GuestbookLogRepository guestbookLogRepository;

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
