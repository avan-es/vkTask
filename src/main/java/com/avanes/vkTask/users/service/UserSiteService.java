package com.avanes.vkTask.users.service;

import com.avanes.vkTask.users.POJO.UserSite;
import com.avanes.vkTask.users.POJO.UserSiteFull;

public interface UserSiteService {
    UserSiteFull getUserSite(Long id);

    UserSiteFull[] getUserSites();

    UserSiteFull addUserSite(UserSite userSite);

    UserSiteFull patchUserSite(UserSite userSite, Long id);

    void deleteUserSite(Long id);
}
