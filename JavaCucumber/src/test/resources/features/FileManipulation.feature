Feature: File Manipulation
  As a user
  I want to be able to create/delete and restore a file

  Background:
    Given I am registered user and logged in

  Scenario: Create a .txt file with content 'megatesting' in it
    When the user creates a txt file on the site named 'a' and containing text 'megatesting'
    Then the user should be able to successfully create it and verify the name on the cloud file list as 'a.txt'

  Scenario: Delete a .txt file with content 'megatesting' in it
    When the user deletes a txt file named 'a.txt'
    Then the user should be able to successfully delete the file 'a.txt' from the cloud file list

  Scenario: Restore a .txt file with content 'megatesting' in it
    When the user restores the deleted file 'a.txt' from the Rubbish Bin
    Then the user should be able to successfully restore the file and seen on the cloud file list as 'a.txt'