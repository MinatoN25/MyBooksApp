import { AppPage } from './app.po';
import { element, by, browser, protractor } from 'protractor';
import { async, tick } from '@angular/core/testing';

describe('My-Books App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display My Favourite Books as application title', () => {
    page.navigateTo();
    let spanElement = element(by.css('span')).getText();    
    expect(spanElement).toEqual('My Favourite Books');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.xpath('/html/body/app-root/auth-login/div/form/div[3]/button[2]/span')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {

    browser.element(by.id('firstName')).sendKeys('Manish');
    browser.element(by.id('lastName')).sendKeys('Kale');
    browser.element(by.id('userId')).sendKeys('908');
    browser.element(by.id('password')).sendKeys('dsdf');
    browser.element(by.xpath('/html/body/app-root/auth-register/div/form/div[7]/button[1]/span')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login user', () => {
    browser.element(by.id('userId')).sendKeys('908');
    browser.element(by.id('password')).sendKeys('dsdf');
    browser.element(by.xpath('/html/body/app-root/auth-login/div/form/div[3]/button[1]/span')).click();
    expect(browser.getCurrentUrl()).toContain('/books/home');
  });

  it('should be able to search books', () => {
    browser.element(by.xpath('/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input')).sendKeys('Harry');
    browser.element(by.xpath('/html/body/app-root/book-dashboard/div/form/mat-form-field[2]/div/div[1]/div/input')).sendKeys(protractor.Key.ENTER);
    browser.sleep(7000);
    expect(browser.getPageSource()).toContain('Harry');
  });

  it('should be able to add books to fav list ', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(15000);
    browser.element(by.className('mat-button-wrapper')).click();
    expect(browser.getPageSource()).toContain('Harry');
  });

  it('should be able to delete books from fav list ', async() => {
    browser.element(by.xpath('/html/body/app-root/mat-toolbar/button[2]/span')).click();
    expect(browser.getCurrentUrl()).toContain('/books/favourite');
    browser.sleep(5000);
    browser.element(by.className('mat-button-wrapper')).click();
    expect(browser.getPageSource()).not.toContain('Harry');
  });
  it('should be able to update user details', () => {
    browser.sleep(3000);
    browser.element(by.xpath('/html/body/app-root/mat-toolbar/button[3]/span')).click();
    browser.element(by.xpath('/html/body/app-root/app-my-profile/div/form/div[1]/mat-form-field/div/div[1]/div/input')).sendKeys('updated');
    browser.element(by.xpath('/html/body/app-root/app-my-profile/div/form/div[2]/mat-form-field/div/div[1]/div/input')).sendKeys('user');
    browser.element(by.xpath('/html/body/app-root/app-my-profile/div/form/div[3]/mat-form-field/div/div[1]/div/input')).sendKeys('908');
    browser.element(by.xpath('/html/body/app-root/app-my-profile/div/form/div[4]/mat-form-field/div/div[1]/div/input')).sendKeys('manish');
    browser.element(by.xpath('/html/body/app-root/app-my-profile/div/form/div[5]/button[1]')).click();
    expect(browser.getPageSource()).toContain('updated');
  });
  
  it('should be able to log out', () =>{
    browser.element(by.xpath('/html/body/app-root/mat-toolbar/button[4]/span')).click();
    expect(browser.getCurrentUrl()).toContain('/login')
  })
});
