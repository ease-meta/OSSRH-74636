package com.open.cloud.auth.oauth2.authserver.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Component
@ConditionalOnProperty(prefix = "auth", name = "token-store", havingValue = "db")
public class AuthJdbcTokenStore extends JdbcTokenStore {

	private static String selectAllToken = "select token_id, token from oauth_access_token";

	private JdbcTemplate jdbcTemplate;

	public AuthJdbcTokenStore(DataSource dataSource) {
		super(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<OAuth2AccessToken> getOnlineUser() {
		List<OAuth2AccessToken> list = new ArrayList<>();
		jdbcTemplate.queryForList(selectAllToken).forEach(map -> {
			Object token = map.get("token");
			if (token instanceof byte[]) {
				OAuth2AccessToken oAuth2AccessToken = this.deserializeAccessToken((byte[]) token);
				if (!oAuth2AccessToken.isExpired()) {
					list.add(this.deserializeAccessToken((byte[]) token));
				}
			}
		});
		return list;
	}


}
