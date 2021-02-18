package xyz.flysium.support.cache;

import java.io.File;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;

/**
 * Support for {@link CacheManager}
 *
 * @author zeno
 */
public final class EhcacheSupport {

  private EhcacheSupport() {
  }

  private static final File DIRECTORY = new File("/var/cache/much_notes");

  static {
    if (!DIRECTORY.exists()) {
      DIRECTORY.mkdirs();
    }
  }

  // 初始化管理器
  private static final CacheManager CACHE_MANAGER = CacheManagerBuilder.newCacheManagerBuilder()
    .with(CacheManagerBuilder.persistence(DIRECTORY))
    .build(true);

  public static CacheManager getCacheManager() {
    return CACHE_MANAGER;
  }

}
