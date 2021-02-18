package xyz.flysium.web.controller.miniprogram;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.flysium.dao.entity.UserAccountInfoDO;
import xyz.flysium.dto.ResultResponse;
import xyz.flysium.dto.UserInfo;
import xyz.flysium.service.UserAccountInfoService;
import xyz.flysium.web.UserInfoHolder;

/**
 * 资产账户管理
 *
 * @author zeno
 */
@RestController
@RequestMapping("/mp/account/info")
@Api(value = "AccountApi", tags = {"资产"})
public class UserAccountInfoController {

  protected static final Logger LOGGER = LoggerFactory.getLogger(UserAccountInfoController.class);

  @Autowired
  private UserAccountInfoService userAccountInfoService;

  @PostMapping("/getBalance")
  @ApiOperation("获取资产信息")
  public ResultResponse<Map<Integer, Long>> getBalance() {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();

    List<UserAccountInfoDO> list = userAccountInfoService.queryBalancesByUid(uid);
    return ResultResponse.success(list.stream().collect(
      Collectors.toMap(UserAccountInfoDO::getAccountType, UserAccountInfoDO::getBalance, (u, v) -> v, HashMap::new)));
  }

  @GetMapping("/balanceEdit")
  @ApiOperation("更新资产信息")
  public ResultResponse<Object> balanceEdit(@Validated @NotNull @RequestParam(name = "type") Integer type,
    @Validated @NotNull @RequestParam(name = "balance") Long balance) {
    UserInfo userInfo = UserInfoHolder.getUserInfo();
    Long uid = userInfo.getUid();
    LOGGER.debug("[更新资产信息] balanceEdit.request [uid={}]: type={}, balance={}", uid, type, balance);
    try {
      userAccountInfoService.directUpdateBalanceByUid(uid, type, balance);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      return ResultResponse.fail("更新失败");
    }

    return ResultResponse.success();
  }

}
