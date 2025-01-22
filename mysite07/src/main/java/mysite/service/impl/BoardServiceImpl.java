package mysite.service.impl;

import jakarta.servlet.http.Cookie;
import mysite.vo.BoardVo;

import java.util.Map;

public interface BoardServiceImpl {
    public void addContents(BoardVo boardVo);

    public BoardVo getContents(Long id);

    public BoardVo getContents(Long id, Long userId);

    public void updateContents(BoardVo boardVo);

    public void deleteContents(Long id, Long userId);

    public Map<String, Object> getContentsList(int page, String keyword);

    public Cookie countView(Long id, String viewData);
}
