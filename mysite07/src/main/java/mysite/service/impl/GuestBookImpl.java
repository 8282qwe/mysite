package mysite.service.impl;

import mysite.vo.GuestbookVo;

import java.util.List;

public interface GuestBookImpl {
    public List<GuestbookVo> getContentsList();

    public void deleteContents(String id, String password);

    public void addContents(GuestbookVo guestbookVo);
}
