package com.scalesec.vulnado;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class User {
  private String id; // User ID
  private String username; // Username
private String id; // User ID
private String username; // Username
  private String hashedPassword; // Hashed password
private String hashedPassword; // Hashed password
  public User(String id, String username, String hashedPassword) {
    this.id = id;
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact();
  }

  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      logger.info(\"Unauthorized access attempt: \" + e.getMessage());
      throw new Unauthorized(e.getMessage());
    }
  }

  public static User fetch(String un) {
    Statement stmt = null;
    User user = null;
    try {
      Connection cxn = Postgres.connection();
      try (Statement stmt = cxn.createStatement()) {
      Logger logger = Logger.getLogger(User.class.getName());
      logger.info(\"Opened database successfully\");

      logger.info(\"Executing query: SELECT * FROM users WHERE username = ? LIMIT 1\");
      PreparedStatement pstmt = cxn.prepareStatement(\"SELECT * FROM users WHERE username = ? LIMIT 1\");
      String userId = rs.getString(\"user_id\");
      if (rs.next()) {
        String userId = rs.getString(\"user_id\");
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(user_id, username, password);
      }
      cxn.close();
    logger.severe(e.getClass().getName() + \": \" + e.getMessage());
      logger.severe(e.getClass().getName() + \": \" + e.getMessage());
    } finally {
    if (stmt != null) stmt.close();
  if (cxn != null) cxn.close();
return user;
}