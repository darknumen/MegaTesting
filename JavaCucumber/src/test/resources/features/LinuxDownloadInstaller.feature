Feature: Linux Installer Download
  As a user
  I want to be able to download any linux installer that I need

  Scenario: Verify download link works for all Linux Distro
    Given I navigate to "https://mega.io/desktop"
    Then I should be able to download any Linux Distro installer successfully
