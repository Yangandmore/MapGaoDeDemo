require 'json'

package = JSON.parse(File.read(File.join(__dir__, '../package.json')))

Pod::Spec.new do |s|
  s.name         = package['name']
  s.version      = package['version']
  s.summary      = package['description']

  s.homepage     = package['repository']['url']
  s.license      = "MIT"
  s.license      = package['license']
  s.authors      = { "Yangandmore" => "i@yangandmore.cc" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => package['repository']['url'] }
  s.source_files  = "**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency 'AMapNavi', "~> 7.2.0"
  s.dependency 'AMapLocation', "~> 2.6.4"
  s.dependency 'AMapSearch', "~> 7.1.0"

end

  
