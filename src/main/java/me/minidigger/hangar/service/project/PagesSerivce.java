package me.minidigger.hangar.service.project;

import me.minidigger.hangar.db.dao.HangarDao;
import me.minidigger.hangar.db.dao.ProjectDao;
import me.minidigger.hangar.db.dao.ProjectPageDao;
import me.minidigger.hangar.db.model.ProjectPagesTable;
import me.minidigger.hangar.model.generated.Project;
import me.minidigger.hangar.model.viewhelpers.ProjectData;
import me.minidigger.hangar.model.viewhelpers.ProjectPage;
import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PagesSerivce {

    private final HangarDao<ProjectPageDao> projectPageDao;

    @Autowired
    public PagesSerivce(HangarDao<ProjectPageDao> projectPageDao) {
        this.projectPageDao = projectPageDao;
    }

    public ProjectPagesTable getPage(long projectId, String pageName) {
        ProjectPagesTable projectPagesTable = projectPageDao.get().getPage(projectId, pageName, null);
        return projectPagesTable;
        // TODO get project page
//        return new ProjectPagesTable(1, OffsetDateTime.now(), projectId, "Home", slug, "# Test\n This is a test", false, null);
    }


    /**
     * Gets a page parents. Must specified either pageName or pageId
     * @param projectId project Id
     * @param pageName pages name
     * @param pageId page id
     * @return List of page parents
     */
    public List<ProjectPagesTable> getPageParents(long projectId, @Nullable String pageName, @Nullable Long pageId) {
        Preconditions.checkArgument(pageName == null && pageId == null, "One of (pageName, pageId) must be nonnull!");
        return projectPageDao.get().getPageParents(projectId, pageName, pageId);
    }

    public void fillPages(ModelAndView mav, long projectId) {
        AtomicInteger pageCount = new AtomicInteger();
        Map<Long, ProjectPage> rootPages = projectPageDao.get().getRootPages(projectId);
        pageCount.addAndGet(rootPages.size());
        Map<ProjectPage, List<ProjectPage>> projectPages = new HashMap<>();
        rootPages.forEach((rootPageId, rootPage) -> {
            List<ProjectPage> childPages = projectPageDao.get().getChildPages(projectId, rootPageId);
            pageCount.addAndGet(childPages.size());
            projectPages.put(rootPage, childPages);
        });

        mav.addObject("rootPages", projectPages);
        mav.addObject("pageCount", pageCount.get());
    }
}

