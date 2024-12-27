package mysite.service;

import mysite.repository.GuestbookRepository;
import mysite.service.impl.GuestBookImpl;
import mysite.vo.GuestbookVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookService implements GuestBookImpl {
    private final GuestbookRepository guestbookRepository;

    public GuestBookService(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    @Override
    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    @Override
    public void deleteContents(String id, String password) {
        guestbookRepository.deleteByIdAndPassword(Long.parseLong(id), password);
    }

    @Override
    public void addContents(GuestbookVo guestbookVo) {
        guestbookRepository.insert(guestbookVo);
    }
}
